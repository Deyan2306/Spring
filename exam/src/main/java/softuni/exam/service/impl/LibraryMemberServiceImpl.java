package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private final static String LIBRARY_MEMBER_FILE_PATH = "src/main/resources/files/json/library-members.json";

    private LibraryMemberRepository libraryMemberRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ValidationUtils validationUtils, ModelMapper modelMapper, Gson gson) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(LIBRARY_MEMBER_FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {

        final StringBuilder stringBuilder = new StringBuilder();

        List<LibraryMemberImportDto> libraryMembers =
                Arrays.stream(this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberImportDto[].class)).collect(Collectors.toList());

        for (LibraryMemberImportDto currentLibraryMember : libraryMembers) {

            // Invalid library member
            if (this.libraryMemberRepository.findFirstByPhoneNumber(currentLibraryMember.getPhoneNumber()).isPresent()
                || !this.validationUtils.isValid(currentLibraryMember)) {

                stringBuilder.append("Invalid library member").append(System.lineSeparator());
                continue;
            }

            LibraryMember toSave = this.modelMapper.map(currentLibraryMember, LibraryMember.class);
            this.libraryMemberRepository.save(toSave);

            stringBuilder.append(String.format("Successfully imported library member %s - %s%n",
                    toSave.getFirstName(),
                    toSave.getLastName()));
        }

        return stringBuilder.toString().trim();
    }
}
