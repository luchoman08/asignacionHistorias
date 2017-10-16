/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorSCP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;

/**
 *
 * @author invitado
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here


        ProyectoAsignacionDesarrolladores proyecto = new ProyectoAsignacionDesarrolladores();
        proyecto.leerPedidos("entrada.txt");
        proyecto.proyecto.inicializarCostos();
       // proyecto.printEntrada();
        proyecto.crearFuncionObjetivo();
        proyecto.adicionarRestricciones();
        System.out.println("habilidad promedio: " + proyecto.proyecto.habilidadPromedio);
        System.out.println("costo promedio: " + proyecto.proyecto.costoPromedio);
        System.out.println(Arrays.toString(proyecto.obtenerResultado()));
        proyecto.traducirResultados(proyecto.obtenerResultado());
    }
}
