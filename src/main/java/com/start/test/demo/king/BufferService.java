package com.start.test.demo.king;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-10-18 14:17
 */
@Service
public class BufferService {

    private static final Map<String, Map<Integer, String>> countryBuffer = new HashMap<>(8);

    private static final Map<String, Map<Integer, String>> jobBuffer = new HashMap<>(6);

    /**
     * @param param
     * @param count
     * @return
     */
    public String getBuffer(String param, Integer count) {
        if (countryBuffer.containsValue(param)) {
            return countryBuffer.get(param).get(count);
        } else {
            return jobBuffer.get(param).get(count);
        }
    }

    /**
     * @return
     */
    public Map<String, Map<Integer, String>> getAll() {
        countryBuffer.putAll(jobBuffer);
        return countryBuffer;
    }

    @PostConstruct
    public void init() {

        // 初始化国家buff
        initCountryBuffer();

        //初始化职业buff
        initJobBuffer();
    }

    /**
     * 初始化国家buff
     */
    private void initCountryBuffer() {
        Map<Integer, String> wei = new HashMap<>(3);
        wei.put(3, "每秒20%开启1300护盾，持续3每秒，CD8秒");
        wei.put(5, "每秒35%开启2000护盾，持续3每秒，CD8秒");
        wei.put(7, "每秒50%开启2800护盾，持续3每秒，CD8秒");
        countryBuffer.put("魏", wei);

        Map<Integer, String> shu = new HashMap<>(3);
        shu.put(3, "增加55%回蓝率");
        shu.put(5, "增加110%回蓝率");
        shu.put(7, "增加220%回蓝率");
        countryBuffer.put("蜀", shu);

        Map<Integer, String> wu = new HashMap<>(2);
        wu.put(3, "吴国普攻会减少敌人15点法力值和等额伤害");
        wu.put(5, "全员普攻会减少敌人28点法力值和等额伤害");
        countryBuffer.put("吴", wu);

        Map<Integer, String> changan = new HashMap<>(3);
        changan.put(3, "结算会获得额外1～2金币");
        changan.put(5, "结算会获得额外2～4金币");
        changan.put(7, "结算会获得额外4～6金币");
        countryBuffer.put("长安", changan);

        Map<Integer, String> jixia = new HashMap<>(2);
        jixia.put(2, "随机复制1名友方角色，继承50%承伤和输出");
        jixia.put(4, "随机复制2名友方角色，继承50%承伤和输出");
        countryBuffer.put("稷下", jixia);

        Map<Integer, String> changcheng = new HashMap<>(3);
        changcheng.put(3, "长城守卫军阵亡后，全员+7%基础属性");
        changcheng.put(5, "长城守卫军阵亡后，全员+9%基础属性");
        changcheng.put(7, "友方阵亡后，全员+12%基础属性");
        countryBuffer.put("长城", changcheng);

        Map<Integer, String> fusahng = new HashMap<>(2);
        fusahng.put(2, "扶桑+170点真实伤害");
        fusahng.put(4, "全员+380点真实伤害");
        countryBuffer.put("扶桑", fusahng);

        Map<Integer, String> yaotian = new HashMap<>(2);
        yaotian.put(2, "尧天+2%攻速/秒");
        yaotian.put(4, "全员+2.5%攻速/秒");
        countryBuffer.put("尧天", yaotian);
    }

    /**
     * 初始化职业buff
     */
    private void initJobBuffer() {
        Map<Integer, String> tank = new HashMap<>(3);
        tank.put(2, "+10%生命值，+30点格挡");
        tank.put(4, "+15%生命值，+60点格挡");
        tank.put(6, "+20%生命值，+90点格挡");
        jobBuffer.put("坦克", tank);

        Map<Integer, String> soldier = new HashMap<>(4);
        soldier.put(2, "+100物防，+10%物理穿透");
        soldier.put(4, "+250物防，+20%物理穿透");
        soldier.put(6, "+500物防，+30%物理穿透");
        soldier.put(8, "+900物防，+40%物理穿透");
        jobBuffer.put("战士", soldier);

        Map<Integer, String> bravo = new HashMap<>(3);
        bravo.put(3, "+15暴击，+30%暴击效果");
        bravo.put(5, "+30暴击，+50%暴击效果");
        bravo.put(7, "+40暴击，+80%暴击效果");
        jobBuffer.put("刺客", bravo);

        Map<Integer, String> Mage = new HashMap<>(3);
        Mage.put(3, "全员获得+350法术攻击力");
        Mage.put(5, "全员获得+650法术攻击力");
        Mage.put(7, "全员获得+1000法术攻击力");
        jobBuffer.put("法师", Mage);

        Map<Integer, String> Shooter = new HashMap<>(3);
        Shooter.put(3, "射手获得+10%攻击力，使敌人-10%攻速，-30%移速");
        Shooter.put(5, "射手获得+15%攻击力，使敌人-20%攻速，-40%移速");
        Shooter.put(7, "射手获得+25%攻击力，使敌人-30%攻速，-50%移速");
        jobBuffer.put("射手", Shooter);

        Map<Integer, String> Auxiliary = new HashMap<>(2);
        Auxiliary.put(2, "全员+50%韧性，+300点法术防御");
        Auxiliary.put(4, "全员+100%韧性，+750点法术防御");
        jobBuffer.put("辅助", Auxiliary);
    }


}
