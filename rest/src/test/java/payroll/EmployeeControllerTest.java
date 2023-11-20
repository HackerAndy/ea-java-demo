package payroll;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired private MockMvc mvc;

	@MockBean private EmployeeRepository repository;

	private Employee employee;

	@Test
	public void getAllEmployeesAllFieldsExists() throws Exception {
		employee = new Employee("Frodo Baggins", "ring bearer");
		employee.setId(1L);
		given(repository.findAll()).willReturn(Arrays.asList(employee));
				
		mvc.perform(get("/employees").accept(MediaTypes.HAL_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
				.andExpect(jsonPath("$._embedded.employeeList[0].id").exists())
				.andExpect(jsonPath("$._embedded.employeeList[0].name").exists())
				.andExpect(jsonPath("$._embedded.employeeList[0].role").exists())
				.andExpect(jsonPath("$._links.self.href", is("http://localhost/employees")))
				.andReturn();
	}
	// @Test
	// public void getByEmployeeIdAllFieldsExists() throws Exception {
	// 	employee = new Employee("Frodo Baggins", "ring bearer");
	// 	employee.setId(1L);
	// 	given(repository.findAll()).willReturn(Arrays.asList(employee));

	// 	mvc.perform(get("/employees/1").accept(MediaTypes.HAL_JSON_VALUE))
	// 			.andDo(print())
	// 			.andExpect(status().isOk())
	// 			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
	// 			.andExpect(jsonPath("$.id").exists())
	// 			.andExpect(jsonPath("$.name").exists())
	// 			.andExpect(jsonPath("$.role").exists())
	// 			.andExpect(jsonPath("$._links.self.href", is("http://localhost/employees")))
	// 			.andReturn();
	// }
}