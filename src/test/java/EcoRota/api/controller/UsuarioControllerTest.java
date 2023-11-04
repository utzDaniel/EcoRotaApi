package EcoRota.api.controller;

import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.controller.dto.response.CriarResponse;
import ecorota.api.controller.dto.response.PreferenciaResponse;
import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private JacksonTester<UsuarioCriarRequest> criarUsuarioJson;

    @Autowired
    private JacksonTester<UsuarioResponse> respUsuarioJson;

    @Test
    @DisplayName("Erro ao cadastrar um usuario")
        //@WithMockUser
    void cadastrar_erro_1() throws Exception {
        var resp = mockMvc.perform(post("/usuario"))
                .andReturn()
                .getResponse();
        assertThat(resp.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Cadastrar usuario com sucesso")
        //@WithMockUser
    void cadastrar_sucesso() throws Exception {

        var objReq = new UsuarioCriarRequest("Daniel", "teste1", "teste1");
        var pref = new PreferenciaResponse(true, true, false, 2);
        var objEsperado = new UsuarioResponse("Daniel", "teste1", pref);
        var jsonEsperado = respUsuarioJson.write(objEsperado).getJson();

        when(usuarioService.cadastrar(objReq))
                .thenReturn(new CriarResponse(2L, new UsuarioResponse(objReq.getNome(), objReq.getEmail(), pref)));

        var resp = mockMvc.perform(
                        post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(criarUsuarioJson.write(objReq).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(resp.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(resp.getContentAsString()).isEqualTo(jsonEsperado);
    }
}