package mx.localmarket.mercadolocal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productor {
    private int id;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String sexo;
    private int edad;
    private int id_contacto;

    public Productor(int id, String nombre, String apPaterno, String apMaterno, String sexo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.ap_paterno = apPaterno;
        this.ap_materno = apMaterno;
        this.sexo = sexo;
        this.edad = edad;
    }
}
