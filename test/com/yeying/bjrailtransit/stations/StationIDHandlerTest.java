package com.yeying.bjrailtransit.stations;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class StationIDHandlerTest {
    @Test
    public void generateID() {
        List<Integer> idList = new LinkedList<>();
        List<String[]> cases = new LinkedList<>();
        cases.add(new String[]{"西直门", "2号线"});
        cases.add(new String[]{"西直门", "4号线"});
        cases.add(new String[]{"西直门", "13号线"});
        cases.add(new String[]{"车公庄", "2号线"});
        cases.add(new String[]{"阜成门", "2号线"});
        cases.add(new String[]{"复兴门", "2号线"});
        cases.add(new String[]{"长椿街", "2号线"});
        cases.add(new String[]{"宣武门", "2号线"});

        for (String[] aCase : cases) {
            int newId = StationIDHandler.generateID(aCase[0], aCase[1]);
            assert !idList.contains(newId);
            System.out.printf("%s %s %d\n", aCase[0], aCase[1], newId);
            idList.add(newId);
        }
    }
}