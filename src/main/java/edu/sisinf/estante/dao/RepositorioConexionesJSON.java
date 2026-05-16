package edu.sisinf.estante.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.sisinf.estante.core.ErrorPersistencia;
import edu.sisinf.estante.modelo.Conexion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio de conexiones persistido en archivo JSON.
 */
public class RepositorioConexionesJSON
        implements IRepositorioConexiones {

    private final File archivo;
    private final ObjectMapper objectMapper;

    /**
     * Constructor del repositorio.
     *
     * @param archivo archivo de persistencia JSON
     */
    public RepositorioConexionesJSON(File archivo) {
        this.archivo = archivo;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(
                SerializationFeature.INDENT_OUTPUT
        );
    }

    @Override
    public List<Conexion> listar() {

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try {

            Conexion[] conexiones =
                    objectMapper.readValue(
                            archivo,
                            Conexion[].class
                    );

            return new ArrayList<>(
                    Arrays.asList(conexiones)
            );

        } catch (IOException e) {

            throw new ErrorPersistencia(
                    "Error al leer conexiones",
                    e
            );
        }
    }

    @Override
    public Optional<Conexion> buscarPorId(String id) {

        return listar()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public Conexion guardar(Conexion conexion)
            throws ErrorPersistencia {

        if (
                conexion.getId() == null
                        || conexion.getId().isBlank()
        ) {
            conexion.setId(
                    UUID.randomUUID().toString()
            );
        }

        List<Conexion> conexiones = listar();

        conexiones.add(conexion);

        persistir(conexiones);

        return conexion;
    }

    @Override
    public void actualizar(Conexion conexion)
            throws ErrorPersistencia {

        List<Conexion> conexiones = listar();

        boolean encontrada = false;

        for (int i = 0; i < conexiones.size(); i++) {

            if (
                    conexiones.get(i)
                            .getId()
                            .equals(conexion.getId())
            ) {

                conexiones.set(i, conexion);

                encontrada = true;

                break;
            }
        }

        if (!encontrada) {

            throw new ErrorPersistencia(
                    "No existe una conexion con id: "
                            + conexion.getId()
            );
        }

        persistir(conexiones);
    }

    @Override
    public void eliminar(String id)
            throws ErrorPersistencia {

        List<Conexion> conexiones = listar();

        boolean eliminada =
                conexiones.removeIf(
                        c -> c.getId().equals(id)
                );

        if (!eliminada) {

            throw new ErrorPersistencia(
                    "No existe una conexion con id: "
                            + id
            );
        }

        persistir(conexiones);
    }

    private void persistir(
            List<Conexion> conexiones
    ) {

        try {

            File directorioPadre =
                    archivo.getParentFile();

            if (
                    directorioPadre != null
                            && !directorioPadre.exists()
            ) {

                directorioPadre.mkdirs();
            }

            objectMapper.writeValue(
                    archivo,
                    conexiones
            );

        } catch (IOException e) {

            throw new ErrorPersistencia(
                    "Error al guardar conexiones",
                    e
            );
        }
    }
}
