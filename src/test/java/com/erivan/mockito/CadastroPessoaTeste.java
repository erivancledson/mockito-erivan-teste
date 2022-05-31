package com.erivan.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CadastroPessoaTeste {

    @Mock //para mockar os dados
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks //injeto
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro(){

        //crio um objeto para o retorno do mock
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("PE", "Caruaru", "Rua 15 de novembro", "principal", "centro");

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("65454545")).thenReturn(dadosLocalizacao);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Erivan", "365232", LocalDate.now(), "65454545");

        assertEquals("Erivan", pessoa.getNome());
        assertEquals("365232", pessoa.getDocumento());
        assertEquals("PE", pessoa.getEndereco().getUf());
    }
}
