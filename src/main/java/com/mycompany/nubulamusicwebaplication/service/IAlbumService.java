package com.mycompany.nubulamusicwebaplication.service;

import com.mycompany.nubulamusicwebaplication.models.Album;

import java.util.List;

public interface IAlbumService {
    void crearAlbum(Album album);

    void actualizarAlbum(Album album, Long usuarioLogueadoId);

    void eliminarAlbum(Long id, Long usuarioLogueadoId);

    Album obtenerAlbum(Long id, Long usuarioLogueadoId);

    List<Album> obtenerAlbumsUsuario(Long usuarioId);
}
