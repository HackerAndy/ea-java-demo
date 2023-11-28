package payroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addEmployeeReturn2XX() throws Exception {
        Employee employee = new Employee("Name", "Role");
        employee.setId(1L);

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
        }
    
    @Test
    void getEmployeeReturnsAllData() throws Exception {
        Long id = 1L;
        Employee employee = new Employee("Name", "Role");
        employee.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        mockMvc.perform(get("/employees/{id}", id)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(employee.getName()))
            .andExpect(jsonPath("$.role").value(employee.getRole()))
            .andDo(print());
    }

    @Test
    void updateEmployeeReturnsUpdatedEmployee() throws Exception {
        Long id = 1L;
        Employee employee = new Employee("Name", "Role");
        employee.setId(id);
        Employee updatedEmployee = new Employee("UpdatedName", "UpdatedRole");
        updatedEmployee.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

         mockMvc.perform(put("/employees/{id}", id).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedEmployee)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))        
        .andExpect(jsonPath("$.name").value(updatedEmployee.getName()))
        .andExpect(jsonPath("$.role").value(updatedEmployee.getRole()))
        .andDo(print());
        }
    

}
