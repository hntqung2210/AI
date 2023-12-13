package lab9;

public class MinimaxAlgo {
	public void execute(Node node) {
		int v = minValue(node);
		System.out.println(v);
		Node bestMove = null;
		for (Node childNode : node.getSuccessors()) {
			int moveValue = minValue(childNode);
			if (moveValue == v && bestMove == null) {
				bestMove = childNode;
			} else if (moveValue < v) {
				bestMove = childNode;
				v = moveValue;
			}
		}
		System.out.println("Best move for MIN player: " + bestMove);
	}

	//	 function MAX-VALUE(state) returns a utility value
//	 if TERMINAL-TEST(state) then return UTILITY(state)
//	 v <- Integer.MIN_VALUE
//	 for each s in SUCCESSORS(state) do
//	 v <- MAX(v, MIN-VALUE(s))
//	 return v
	public int maxValue(Node node) {
		if (node.isTerminal()) {
			return getUtilityValue(node);
		}

		int maxValue = Integer.MIN_VALUE;
		for (Node childNode : node.getSuccessors()) {
			int minValue = minValue(childNode);
			maxValue = Math.max(maxValue, minValue);
		}
		return maxValue;
	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node) {
		if (node.isTerminal()) {
			return getUtilityValue(node);
		}

		int minValue = Integer.MAX_VALUE;
		for (Node childNode : node.getSuccessors()) {
			int maxValue = maxValue(childNode);
			minValue = Math.min(minValue, maxValue);
		}
		return minValue;
	}

	private int getUtilityValue(Node node) {
		if (isMAXPlayerTurn(node)) {
			return 1;
		}else
			return 0;
	}

	private boolean isMAXPlayerTurn(Node node) {
		return (node.getData().size() % 2 == 0);
	}
}