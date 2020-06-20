package at.fhj.ima.spuddy.service

import at.fhj.ima.spuddy.entity.District
import at.fhj.ima.spuddy.entity.Sport
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileService {

    fun importSportJson(multipartFile: MultipartFile) : List<Sport>{
        var file = File(System.getProperty("java.io.tmpdir")+"sportJsonImport.json")
        multipartFile.transferTo(file)

        var objectMapper: ObjectMapper = ObjectMapper()
        return objectMapper.readValue(file)
    }

    fun importDistrictJson(multipartFile: MultipartFile) : List<District>{
        var file = File(System.getProperty("java.io.tmpdir")+"districtJsonImport.json")
        multipartFile.transferTo(file)
        var objectMapper: ObjectMapper = ObjectMapper()
        return objectMapper.readValue(file)
    }
}