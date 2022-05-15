package mx.localmarket.mercadolocal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {
    private int id;
    private String nombre;
    private int id_categoria;
    private String descripcion;
}
