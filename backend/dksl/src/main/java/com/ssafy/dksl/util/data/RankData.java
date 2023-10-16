package com.ssafy.dksl.util.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RankData {

    final Map<String, Integer> rank = new HashMap<>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
    }};
}
