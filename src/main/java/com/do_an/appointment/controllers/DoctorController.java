package com.do_an.appointment.controllers;
import com.do_an.appointment.dtos.DoctorDTO;
import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.responses.DoctorListResponse;
import com.do_an.appointment.responses.DoctorResponse;
import com.do_an.appointment.responses.UserResponse;
import com.do_an.appointment.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    // Đăng ký doctor 
    @PostMapping("/register")
    public ResponseEntity<?> createDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO,
            BindingResult result
    ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessager = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessager);
            }
            Doctor doctor = doctorService.crateDoctor(doctorDTO);
            return ResponseEntity.ok(doctor);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Upload file ảnh doctor
    @PutMapping(value = "upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImageDoctor(
        @PathVariable("id") Long id,
        @RequestParam("file") MultipartFile file
    ){
        try{
            if (file.getSize() == 0 || file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("File is empty! Please upload a valid file.");
            }
            //Kiểm tra kích thước file và định dạng file
            if (file.getSize() > 10 * 1024 * 1024) {// > 10MB
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).
                        body("File is too large! Maximum size is 10MB");
            }
            // Kiểm tra định dạng file
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image");
            }
            //Lưu file và cập nhật thumbnail trong DTO
            String filename = storeFile(file);
            Doctor doctor = doctorService.uploadImageDoctor(id, filename);
            return ResponseEntity.ok(doctor);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Lưu file
    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        //Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
        // Đường dẫn đến thư mục mà bạn muốn lưu file
        Path uploadDir = Paths.get("uploads");
        //Kiểm tra và tạo thư mục nếu không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        //Đường dẫn đầy đủ đến file
        Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable("imageName") String imageName) {
        try {
            java.nio.file.Path imagePath = Paths.get("uploads/"+imageName);
            //Tạo một đối tượng UrlResource từ đường dẫn hình ảnh, cho phép tải tệp hình ảnh từ hệ thống tệp của server.
            UrlResource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        // hình ảnh sẽ được trả về dưới định dạng JPEG.
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.ok()
                        //Nếu hình ảnh không tồn tại, hệ thống sẽ trả về một hình ảnh mặc định
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new UrlResource(Paths.get("uploads/notfound.jpg").toUri()));
                //return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable("id") Long id){
        boolean isDeleted = doctorService.deleteDoctorById(id);

        if (isDeleted) {
            return ResponseEntity.ok().build();  // Trả về 200 OK nếu xóa thành công
        } else {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getAllDoctors(@RequestParam("page") int page,
                                           @RequestParam("limit") int limit,
                                           @RequestParam(defaultValue = "0", name = "specialty_id") Long specialtyId
    ){
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,
                Sort.by("id").ascending()
        );
        Page<Doctor> doctorPage = doctorService.getAllDoctors(pageRequest,specialtyId);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(DoctorResponse::fromDoctor);
        List<DoctorResponse> doctorResponses = doctorResponsePage.getContent();
        int totalPages = doctorResponsePage.getTotalPages();
        return ResponseEntity.ok(DoctorListResponse.builder()
                .listDoctors(doctorResponses)
                .totalPages(totalPages)
                .build());
    }

    @GetMapping("{user_id}")
    public ResponseEntity<?> getDoctorByUserId(@PathVariable("user_id") Long userId){
        Doctor doctor = doctorService.getDoctorByUserId(userId);
        DoctorResponse doctorResponse = DoctorResponse.fromDoctor(doctor);
        return ResponseEntity.ok(doctorResponse);
    }

    @PutMapping("{doctor_id}")
    public ResponseEntity<?> updateDoctor(@PathVariable("doctor_id") Long doctorId,
                                          @RequestBody DoctorDTO doctorDTO){
        try {
            Doctor doctor =doctorService.updateDoctor(doctorId, doctorDTO);
            DoctorResponse doctorResponse = DoctorResponse.fromDoctor(doctor);
            return ResponseEntity.ok(doctorResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
