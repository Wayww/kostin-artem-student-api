package com.kostinartem.studentapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository repository;

    @Test
    void createsStudent() throws Exception {
        Student saved = student(1L, "Artem Kostin");
        when(repository.save(any(Student.class))).thenReturn(saved);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Artem Kostin\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/students/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Artem Kostin"));
    }

    @Test
    void listsStudents() throws Exception {
        when(repository.findAll()).thenReturn(List.of(student(1L, "Artem Kostin")));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Artem Kostin"));
    }

    @Test
    void returnsStudentById() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(student(1L, "Artem Kostin")));

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Artem Kostin"));
    }

    @Test
    void returnsNotFoundForMissingStudent() throws Exception {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/students/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatesStudent() throws Exception {
        Student existing = student(1L, "Old Name");
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Artem Kostin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Artem Kostin"));
    }

    @Test
    void deletesStudent() throws Exception {
        when(repository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());

        verify(repository).deleteById(1L);
    }

    private Student student(Long id, String name) {
        Student student = new Student(name);
        student.setId(id);
        return student;
    }
}
