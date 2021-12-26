package life.sunhao;

import life.sunhao.pojo.MajiangCard;
import life.sunhao.pool.MajiangPool;
import life.sunhao.service.MajiangService;
import life.sunhao.utils.MajiangUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello world");
        MajiangService.createMajiangAll();
        MajiangService.initTouch();

        List<MajiangCard> leftCardList = MajiangPool.leftCardList;
        List<MajiangCard> handCardList = MajiangPool.handCardList;
        System.out.println("整理后的手牌：");
        MajiangUtil.print(handCardList);
        System.out.println("-----------");

        System.out.println("剩下的麻将牌：");
        MajiangUtil.print(leftCardList);
        System.out.println("-----------");
        System.out.println("good bye");
    }

}
