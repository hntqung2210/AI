package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.getData().add(val);
	}

	public void addAll(List<Integer> data) {
		this.getData().addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> successors = new ArrayList<>();
		for (Integer pile : this.getData()) {
			if (pile > 1) {
				for (int i = 1; i < pile; i++) {
					Node childNode = new Node();
					List<Integer> childData = new ArrayList<>(this.getData());
					childData.remove(pile);
					childData.add(i);
					childData.add(pile - i);
					childNode.addAll(childData);
					if (!successors.contains(childNode)) {
						successors.add(childNode);
					}
				}
			}
		}
		return successors;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		for (Integer pile : this.getData()) {
			if (pile > 1) {
				return false;
			}
		}
		return true;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.getData(), DESCOMPARATOR);
		return this.getData().toString();
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

}