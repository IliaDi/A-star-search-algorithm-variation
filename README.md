# A-star-search-Variation
To run </br>
1)From code folder: java Taxi path/to/csvs/ 
</br>
2) To convert taxiI.out files to kml files (from code folders)
	 ./make_kml.sh code/folder_with_out_files result.kml
	 
</br>
Program simulating the process of matching clients with the closest taxi. </br>
Input: csv files with client, taxi and street coordinates. </br>
Java program that finds the taxi that has to cover the shortest path to the client. Shortest path is calculated using a variation on A* algoritmh, that returns not only one, but possibly more alternatives for shortest paths that cost the same. When during the search the algorithm can reach a node that is already visited with the same cost, keeps that information for the previous and new path to arrive there. 
</br>
Output: Shortest paths on a .kml file, to be visualized using for example google maps.
