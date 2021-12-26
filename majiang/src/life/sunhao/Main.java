package life.sunhao;

import life.sunhao.pojo.MajiangCard;
import life.sunhao.pool.MajiangPool;
import life.sunhao.service.MajiangService;
import life.sunhao.service.WinService;
import life.sunhao.utils.MajiangUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello world");
        // 初始化
        MajiangService.createMajiangAll();
        // 第一次摸牌
        MajiangService.initTouch();
        // 再摸一张
        MajiangService.touchOne();
        // 整牌
        MajiangPool.handCardList = MajiangService.arrangeCardList(MajiangPool.handCardList);

        List<MajiangCard> handCardList = MajiangPool.handCardList;
        System.out.println("整理后的手牌：");
        MajiangUtil.print(handCardList);
        System.out.println("-----------");

        // 判断是否胡牌
        MajiangPool.handCardList = testCardList1();
        boolean isWin = WinService.isWinNoGod(MajiangPool.handCardList);
        System.out.println(isWin);
        MajiangPool.handCardList = testCardList2();
        isWin = WinService.isWinNoGod(MajiangPool.handCardList);
        System.out.println(isWin);
        MajiangPool.handCardList = testCardList3();
        isWin = WinService.isWinNoGod(MajiangPool.handCardList);
        System.out.println(isWin);
        System.out.println("good bye");

    }

    public static List<MajiangCard> testCardList1() {
        String dataStr = "1万,2万,3万,1饼,2饼,3饼,东风,东风,东风,3条,4条,5条,南风,南风";
        return MajiangUtil.nameCnStrToCardList(dataStr);
    }

    public static List<MajiangCard> testCardList2() {
        String dataStr = "1万,2万,3万,1饼,2饼,3饼,东风,东风,东风,3条,4条,5条,南风,西风";
        return MajiangUtil.nameCnStrToCardList(dataStr);
    }

    public static List<MajiangCard> testCardList3() {
        String dataStr = "1万,2万,3万,1饼,2饼,3饼,东风,东风,东风,3条,4条,5条,3万,3万";
        return MajiangUtil.nameCnStrToCardList(dataStr);
    }

}
