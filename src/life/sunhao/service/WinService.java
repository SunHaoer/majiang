package life.sunhao.service;

import life.sunhao.pojo.MajiangCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: WinService
 * @Author: sunhao
 * @Date 2021-12-26 19:59
 */
public class WinService {

    private static boolean isWinFlag = false;

    /**
     * 判断是否胡牌，暂时不引入财神概念，也无吃无碰
     * 对对胡等特殊情况也暂时不考虑
     * 正常手牌数 14=3+3+3+2
     * @return
     */
    public static boolean isWinNoGod(List<MajiangCard> majiangCardList) {
        if (majiangCardList.size() % 3 != 2) {
            throw new RuntimeException("手牌数量不对!");
        }
        // 整理
        majiangCardList = MajiangService.arrangeCardList(majiangCardList);
        // 3 张牌的组合
        List<MajiangCard>[] winThree = new List[4];
        for (int i = 0; i < winThree.length; i++) {
            winThree[i] = new ArrayList<>();
        }
        // 2 张牌的组合
        List<MajiangCard> winTwo = new ArrayList<>();
        // 当前判断的下标
        int judgeIndex = 0;
        // 开始判断胡牌
        isWinFlag = false;
        dfs(majiangCardList, winThree, winTwo, judgeIndex);

        return isWinFlag;
    }

    /**
     * 把当前判断的麻将牌放入对应判断集合, 并持续递归搜索
     * @param majiangCardList 需要判断的手牌
     * @param winThree 3张牌的组合
     * @param winTwo 2张牌的组合
     * @param judgeIndex 当前判断麻将牌的下标
     */
    public static void dfs(List<MajiangCard> majiangCardList, List<MajiangCard>[] winThree, List<MajiangCard> winTwo, int judgeIndex) {
        if (isWinFlag) {
            return;
        }
        if (judgeIndex == majiangCardList.size()) {
            // 检查结束
            isWinFlag = true;
            return;
        }
        // 当前正在判断的麻将牌
        MajiangCard majiangCard = majiangCardList.get(judgeIndex);

        // 放入 3 个组合的
        for (int i = 0; i < winThree.length; i++) {
            if (winThree[i].size() == 3) {
                continue;
            }
            if (winThree[i].size() == 0) {
                // 这个组合是空的, 可以放
                winThree[i].add(majiangCard);
                dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                winThree[i].remove(majiangCard);
            }
            if (winThree[i].size() == 1) {
                // 这个组合里有 1 张牌
                if (winThree[i].get(0).getCode().intValue() == majiangCard.getCode().intValue()) {
                    // 相同, 可以放
                    winThree[i].add(majiangCard);
                    dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                    winThree[i].remove(majiangCard);
                }
                if (Math.abs(winThree[i].get(0).getCode() - majiangCard.getCode()) == 1) {
                    // 相连, 可以放
                    winThree[i].add(majiangCard);
                    dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                    winThree[i].remove(majiangCard);
                }
            }
            if (winThree[i].size() == 2) {
                // 这个组合里有 2 张牌
                if (winThree[i].get(0).getCode().intValue() == winThree[i].get(1).getCode().intValue()) {
                    // 相同的
                    if (winThree[i].get(0).getCode().intValue() == majiangCard.getCode().intValue()) {
                        winThree[i].add(majiangCard);
                        dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                        winThree[i].remove(majiangCard);
                    }
                } else {
                    // 相连的
                    if (majiangCard.getCode() - Integer.min(winThree[i].get(0).getCode(), winThree[i].get(1).getCode()) == 1) {
                        // 比最小值小 1
                        winThree[i].add(majiangCard);
                        dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                        winThree[i].remove(majiangCard);
                    } else if (majiangCard.getCode() - Integer.max(winThree[i].get(0).getCode(), winThree[i].get(1).getCode()) == 1) {
                        // 比最大值大 1
                        winThree[i].add(majiangCard);
                        dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                        winThree[i].remove(majiangCard);
                    }
                }
            }
        }
        // 处理 2 张牌的组合
        if (winTwo.size() == 0) {
            // 空的, 可以放
            winTwo.add(majiangCard);
            dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
            winTwo.remove(majiangCard);
        }
        if (winTwo.size() == 1) {
            // 这个组合里有 1 张牌
            if (winTwo.get(0).getCode().intValue() == majiangCard.getCode().intValue()) {
                winTwo.add(majiangCard);
                dfs(majiangCardList, winThree, winTwo, judgeIndex + 1);
                winTwo.remove(majiangCard);
            }
        }
    }

}
