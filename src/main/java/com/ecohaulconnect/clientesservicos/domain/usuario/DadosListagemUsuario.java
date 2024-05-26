package com.ecohaulconnect.clientesservicos.domain.usuario;

public record DadosListagemUsuario(
        Long id,
        String email
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername());
    }
}
