package Game;

import java.util.Queue;

import java.util.LinkedList;

class GenerateGraph {

	int[][] AdjacencyMatrix;
	int startVtx;
	int endVtx;

	//we get the components to create a graph
	GenerateGraph(int[][] AdjacencyMatrix, int startVtx, int endVtx) {

		this.AdjacencyMatrix = AdjacencyMatrix;
		this.startVtx = startVtx;
		this.endVtx = endVtx;
	}

	//implement dijsktra's algorithm with adjacency matrix
	public Queue<Integer> dijskstra(int[][] AdjacencyMatrix, int startVtx, int endVtx) {

		Queue<Integer> path = new LinkedList<>();
		Queue<Integer> visitNext = new LinkedList<>();

		int ancestor[] = new int[AdjacencyMatrix.length];
		int travel[] = new int[AdjacencyMatrix.length];

		boolean isVisited[] = new boolean[AdjacencyMatrix.length];

		for (int i = 0; i < AdjacencyMatrix.length; i++) {

			for (int j = 0; j < AdjacencyMatrix.length; j++) {

				if (AdjacencyMatrix[i][j] == 0) {
					AdjacencyMatrix[i][j] = 999999;
				}

				if (i + 2 == startVtx) {

					isVisited[i] = true;
					travel[j] = AdjacencyMatrix[i][j];

					if (AdjacencyMatrix[i][j] != 999999) {
						ancestor[j] = i;
						visitNext.add(j);
					}
				}
			}
		}
		ancestor = check(travel, ancestor, AdjacencyMatrix, isVisited, visitNext);
		path = generatePath(ancestor, startVtx - 2, endVtx - 2);
		path.add(endVtx);
		System.out.print(path.peek());
		return path;
	}

	public int[] check(int[] travel, int[] ancestor, int[][] AdjacencyMatrix, boolean[] isVisited,
			Queue<Integer> visitNext) {

		while (!visitNext.isEmpty()) {

			int toEvaluate = visitNext.poll();

			if (isVisited[toEvaluate]) {
				check(travel, ancestor, AdjacencyMatrix, isVisited, visitNext);
			}

			else {

				isVisited[toEvaluate] = true;
				int weight = travel[toEvaluate];

				for (int i = 0; i < AdjacencyMatrix.length; i++) {
					for (int j = 0; j < AdjacencyMatrix.length; j++) {

						if (i == toEvaluate) {

							visitNext.add(j);

							if (travel[j] > AdjacencyMatrix[i][j] + weight) {

								ancestor[j] = i;
								travel[j] = AdjacencyMatrix[i][j] + weight;
							}
						}
					}
				}
				check(travel, ancestor, AdjacencyMatrix, isVisited, visitNext);

			}
		}

		return ancestor;
	}

	//generates the queue to follow to get to the solution
	public Queue<Integer> generatePath(int[] ancestor, int startVtx, int endVtx) {

		Queue<Integer> path = new LinkedList<>();

		if (ancestor[endVtx] == startVtx) {
			return path;
		} else {
			path.add(ancestor[endVtx] + 2);
			endVtx = ancestor[endVtx];
			generatePath(ancestor, startVtx, endVtx);
			return path;
		}

	}

}