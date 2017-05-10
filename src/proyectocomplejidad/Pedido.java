/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocomplejidad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucho
 */
public class Pedido {
    public int cantidadUnidades;
    public int tamanioUnidad;
    
    public Pedido(int cantidadUnidades, int tamanioUnidad){
        this.cantidadUnidades = cantidadUnidades;
        this.tamanioUnidad = tamanioUnidad;
    }
    public Pedido(Pedido origen){
        this.cantidadUnidades = origen.cantidadUnidades;
        this.tamanioUnidad = origen.tamanioUnidad;
    }
}
