package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.entity.File
import at.fhj.ima.spuddy.entity.Sport
import at.fhj.ima.spuddy.repository.FileRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.io.File as JavaFile


@Service
class FileService(val fileRepository: FileRepository) {

    fun importSportJson(multipartFile: MultipartFile) : List<Sport>{
        var file = JavaFile(System.getProperty("java.io.tmpdir")+"sportJsonImport.json")
        multipartFile.transferTo(file)

        var objectMapper: ObjectMapper = ObjectMapper()
        return objectMapper.readValue(file)
    }

    fun importDistrictJson(multipartFile: MultipartFile) : List<District>{
        var file = JavaFile(System.getProperty("java.io.tmpdir")+"districtJsonImport.json")
        multipartFile.transferTo(file)
        var objectMapper: ObjectMapper = ObjectMapper()
        return objectMapper.readValue(file)
    }

    fun save(file: File): File {
        return fileRepository.save(file)
    }

    @Transactional
    fun createFile(dto: MultipartFile, creator : String): File {
        val file = convertMultipartFileToFile(dto)
        file.creator = creator
        this.save(file)
        val path = this.retrievePath(file.id!!).toAbsolutePath()
        if (!Files.exists(path)) {

            Files.createFile(path)
        }

        Files.write(path, dto.bytes, StandardOpenOption.WRITE)
        return file
    }

    fun convertMultipartFileToFile(dto: MultipartFile): File {
        return File(contentType = dto.contentType, size = dto.size, originalFileName = dto.originalFilename)
    }

    fun retrievePath(id: Int): Path {
        return Paths.get("src/main/resources/static/files/$id")
    }

    fun findById(id: Int): File {
        return fileRepository.findById(id).get()
    }

    fun delete(id: Int) {
        fileRepository.delete(findById(id))
    }

    fun findByUploadingUser(uploadingUser : String) : File? {
        return fileRepository.findByUploadingUser(uploadingUser)
    }

}