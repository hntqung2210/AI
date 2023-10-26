package agent_AB; 

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	// thêm tác nhân nhận vị trí môi trường và con agent nhận 2 dữ kiện
	public void addAgent(Agent agent, String location) {
		this.agent= agent;
		envState.setAgentLocation(location);
		
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// UCập nhật trạng thái môi trường khi tác nhân thực hiện một hành động
	//máy hút bụi dọn thì nó sẽ sạch , không dọn thì bẩn
	public EnvironmentState executeAction(Action action) {
		if(action==  Environment.SUCK_DIRT) {
			String agentLocation = envState.getAgentLocation();
			envState.setLocationState(agentLocation, LocationState.CLEAN);
		}else if(action== Environment.MOVE_LEFT) {
			envState.setAgentLocation(LOCATION_A);
		}else if(action== Environment.MOVE_RIGHT) {
			envState.setAgentLocation(LOCATION_B);
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> tại vị trí hiện tại khi con bot ở trong đó
	public Percept getPerceptSeenBy() {
		Percept p = new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
		return p;
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
