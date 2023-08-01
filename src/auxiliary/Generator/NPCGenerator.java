package auxiliary.Generator;

import Content.NPC.Base.INPC;
import Content.NPC.Base.NPC;
import Content.Player;
import auxiliary.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NPCGenerator {
    private final static String NPC_PACKAGE = "Content.NPC";
    private static List<NPCGeneratorProperty> npcGeneratorProperties;

    public static void buildNPC(Player player) {
        int x = CommonUtils.getRandomNumBetween(CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_WIDTH + 100);
        while (!buildEnemy(x,ContentConstants.GROUND_HEIGHT - ContentConstants.CHARACTER_HEIGHT, player)) ;
    }
    private static void loadNPCGeneratorProperty() {
        Set<Class<?>> classes = ClassLoaderUtils.loadClassByPackage(NPC_PACKAGE);
        classes.forEach(classType -> {
            if (!NPC.class.isAssignableFrom(classType)) {
                return;
            }
            INPC ann = classType.getAnnotation(INPC.class);
            if (ann == null) {
                return;
            }
            Constructor<? extends NPC> constructor;
            try {
                constructor = (Constructor<? extends NPC>) classType.getConstructor(int.class, int.class,int.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("获取" + classType.getTypeName() + "构造器异常");
                return;
            }
            npcGeneratorProperties.add(new NPCGeneratorProperty(ann, classType.getSimpleName(), constructor));
        });
    }
    private static List<NPCGeneratorProperty> getEnemyGeneratorProperties() {
        if (npcGeneratorProperties == null) {
            synchronized (NPCGenerator.class) {
                if (npcGeneratorProperties == null) {
                    npcGeneratorProperties = new ArrayList<>();
                    loadNPCGeneratorProperty();
                }
            }
        }
        return npcGeneratorProperties;
    }
    private static boolean buildEnemy(int x, int y, Player player) {
        List<NPCGeneratorProperty> propertyList = getEnemyGeneratorProperties();
        int i = CommonUtils.getRandomNumBetween(0, propertyList.size()-1);//在6种敌人中随机选择一种
        NPCGeneratorProperty property = propertyList.get(i);//获取随机那一种的属性
        //概率
        int nextInt = CommonUtils.getRandomNumBetween(1, 100);
        if (property.getProbability() < nextInt) {
            return false;
        }
        build(property,x,y);
        return true;
    }
    private static void build(NPCGeneratorProperty property, int x, int y) {
        int distance = CommonUtils.getRandomNumBetween(20, 80);//间隔距离
        int count = CommonUtils.getRandomNumBetween(1, property.getNumber());//个数
        int xSpeed ;
        do{
            xSpeed = CommonUtils.getRandomNumBetween(-1,2);
        }while (xSpeed == 0);
        if(xSpeed>0)
            x = CommonConstants.FRAME_WIDTH - x;
        createNPC(property, count, distance, x, y,xSpeed);
    }
    private static void createNPC(NPCGeneratorProperty property, int count, int distance, int x, int y,int xSpeed) {
        if (count == 0) {
            return;
        }
        NPC npc;
        try {
            npc = property.getConstructor().newInstance(x, y, xSpeed);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取" + property.getClassName() + "实例异常");
        }
        ElementBean.NPC.getService().add(npc);
        //间隔距离
        x += distance;
        count--;
        createNPC(property, count, distance, x, y,xSpeed);
    }

}
