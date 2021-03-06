package mx.localmarket.mercadolocal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    private int id;
    private String nombre;
    private String imagen;
}
