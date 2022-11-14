package lotto.model;

import java.util.List;

public class Revenue {
    private int reward = 0;

    private int oneRevenue(WinDetail winDetail){
        return winDetail.reward();
    }

    public int revenue(List<WinDetail> result){
        for(WinDetail win : result) {
            reward += oneRevenue(win);
        }
        return reward;
    }
}