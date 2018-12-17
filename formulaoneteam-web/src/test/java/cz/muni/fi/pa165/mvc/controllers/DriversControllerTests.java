package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DriversControllerTests extends BaseControllerTest<DriversController> {

    @Mock
    private DriverFacade driverFacadeMock;

    @InjectMocks
    private DriversController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void list_whenDriversArePresent_attributeEquals() throws Exception {
        //When
        List<DriverDTO> drivers = Arrays.asList(createListItemDto(), createListItemDto());
        when(driverFacadeMock.getAllDrivers()).thenReturn(drivers);

        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(model().attributeExists("drivers"))
                .andExpect(model().attribute("drivers", drivers));
    }

    private DriverDTO createListItemDto() {
        DriverDTO driverDto = new DriverDTO();
        driverDto.setName("John");
        driverDto.setSurname("Doe");
        driverDto.setEmail("john@doe.com");
        driverDto.setNationality("American");
        driverDto.setBirthday(createDate(2, 9, 1989));
        driverDto.setDriverStatus(DriverStatus.MAIN);
        return driverDto;
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    protected DriversController getController() {
        return controller;
    }
}
