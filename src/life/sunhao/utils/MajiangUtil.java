package life.sunhao.utils;

import life.sunhao.pojo.MajiangCard;
import life.sunhao.service.MajiangService;

import java.util.*;

/**
 * @Name: MajiangUtil
 * @Author: sunhao
 * @Date 2021-12-26 15:17
 */
public class MajiangUtil {

    /**
     * 打印麻将牌
     * @param cardList
     */
    public static void print(List<MajiangCard> cardList) {
        // 整理麻将牌
        cardList = MajiangService.arrangeCardList(cardList);
        // 打印
        System.out.print(cardList.get(0).getNameCn() + " ");
        for (int i = 1; i < cardList.size(); i++) {
            // 上一张麻将牌
            MajiangCard lastCard = cardList.get(i - 1);
            // 当前麻将牌
            MajiangCard nowCard = cardList.get(i);
            if (!nowCard.getType().equals(lastCard.getType())) {
                // 类型不同了， 换行
                System.out.println();
            }
            // 打印当前麻将牌
            System.out.print(nowCard.getNameCn() + " ");
        }
        System.out.println();
    }

    /**
     * 麻将牌中文名根据逗号分割, 转为麻将 list
     * @return
     */
    public static List<MajiangCard> nameCnStrToCardList(String nameCnStr) {
        List<MajiangCard> cardList = new ArrayList<>();
        List<String> nameCnList = Arrays.asList(nameCnStr.split(","));
        for (String nameCn : nameCnList) {
            cardList.add(new MajiangCard(nameCn));
        }
        return cardList;
    }

    /**
     * list 转 queue
     * @param majiangCardList
     * @return
     */
    public static Queue<MajiangCard> listToQueue(List<MajiangCard> majiangCardList) {
        Queue<MajiangCard> result = new LinkedList<>();
        for (MajiangCard card : majiangCardList) {
            result.add(card);
        }
        return result;
    }

}
