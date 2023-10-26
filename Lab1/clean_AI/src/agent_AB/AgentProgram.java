package agent_AB;

import agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
//lấy trạng thái môi trường
		if(p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}
//lấy vị trí môi trường
		else if(p.getAgentLocation() == Environment.LOCATION_A) {
			return Environment.MOVE_RIGHT;
		}
		else if(p.getAgentLocation() == Environment.LOCATION_B) {
			return Environment.MOVE_LEFT;
		}
			return NoOpAction.NO_OP;
		
		}
	
	}
