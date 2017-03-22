package com.n26.trial.statistics.rest;

import com.n26.trial.statistics.Application;
import com.n26.trial.statistics.domain.Statistic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rafael on 21/03/2017.
 */
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticRestServiceTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

    assertNotNull("the JSON message converter must not be null",
            this.mappingJackson2HttpMessageConverter);
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }

  @Test
  public void test_insert_statistic() throws Exception {
    Statistic statistic = new Statistic();
    statistic.setAmount(10.0);
    statistic.setTimestamp(Calendar.getInstance());

    postInsertStatistic(statistic);
  }

  private void postInsertStatistic(Statistic statistic) throws Exception {
    mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json(statistic)))
            .andDo(print())
            .andExpect(status().isCreated());
  }

  @Test
  public void test_get_all_statistics() throws Exception {
    Statistic statistic = new Statistic();
    statistic.setAmount(10.0);
    statistic.setTimestamp(Calendar.getInstance());

    postInsertStatistic(statistic);

    mockMvc.perform(get("/statistics")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$.sum", is(10.0)))
            .andExpect(jsonPath("$.count", is(1)))
            .andExpect(jsonPath("$.min", is(10.0)))
            .andExpect(jsonPath("$.avg", is(10.0)))
            .andExpect(jsonPath("$.max", is(10.0)));
  }
}
