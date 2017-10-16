#Descripciones generales de el proyecto, vale decir que este esta en estado de maduración por lo cual todo lo aquí presente esta sujeto a cambios

Definiciones:  
x el numero de características  
d el numero de desarrolladores   
h el numero de historias  
6 características  

El archvivo de entrada es "entrada.txt"

El formato será el siguiente:


Primera línea indicara cuantos desarrolladores hay (d)
Luego irá la línea que indica cuantas historias hay

En las siguientes d lineas se describirán las caracteristicas de cada desarrollador, una característica es una habilidad del 0.1 al 1 la cual indica que tan bueno es un desarrollador d en una característica c

Ej: Asumiendo tres caracteristicas (backend, frontend, ux)  
  
  Un desarrollador mediocre: 0.5 0.5 0.5  
  Un desarrollador muy nuevo: 0.1 0.1 0.1  
  Un desarrollador muy habilidoso: 1 1 1   

Luego habrá una linea de gracia que no tendra otra utilidad que separar las caracteristicas de los desarrolladores de la de las hitorias, esta linea sera escapada al momento de leer los datos y puede llevar cualquier caracter o conjunto de caracteres.


En las siguientes líneas se describirán las complejidades que cada historia tiene del 0 al 10 sobre una característica
c

Ej: Asumiendo tres caracteristicas (backend, frontend, ux)  
  
  Una historia de dificultad media: 5 5 5  
  Una historia muy facl :  1 1 1  
  Una historia muy dificil: 10 9 9  
  
  
El costo que cada desarrollador tendrá para resolver una historia será máximo de 100x y mínimo de 10x 

Ej: Asignando la historia muy dificil al desarrollaodr mediocre, el costo correspondiente es el siguiente:

total = 10 / 0.5 + 9 / 0.5 + 9 / 0.5 = 56

Asignando la historia muy dificil al desarrollaodr muy habilidoso, el costo correspondiente es el siguiente:

total = 10 / 1 + 9 / 1 + 9 / 1 = 28

Asignando la historia muy dificil al desarrollaodr muy nuevo, el costo correspondiente es el siguiente:

total = 10 / 0.1 + 9 / 0.1 + 9 / 0.1 = 280

El tiempo que se tarda en calcular el costo de cada desarrollador por cada historia es O(n^3)

La variable de decisión es si el desarrollador hará la historia o no 
La formula a maximizar es el costo total del proyecto, es decir la suma de cada variable de decisión por el costo de cada desarrollador.

Habrá un conjunto de restricciones que garantizara la equitatividad de numero de historias asignadas a cada desarrollador, un modelo muy simple donde <= a techo(h/d) por cada sumatoria de variables de decisión asociadas a 
cada desarrollador

Deberá haber un conjunto de restricciones que trataran de garantizar la equitatividad del numero de puntos 
asignados a cada desarrollador, un modelo en el cual intervendrá cuanto es el promedio (aún esta en desarrollo)
