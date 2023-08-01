package auxiliary.Generator;

import Content.NPC.Base.INPC;
import Content.NPC.Base.NPC;

import java.lang.reflect.Constructor;

public class NPCGeneratorProperty {
    private int probability;//概率
    private int number;
    private String className;//类名
    private Constructor<? extends NPC> constructor;//类构造器
    public NPCGeneratorProperty(INPC npcAnn,String className,Constructor<? extends NPC> constructor){
        this.probability = npcAnn.probability();
        this.number = npcAnn.number();
        this.constructor = constructor;
        this.className = className;
    }
    public String getClassName() {
        return className;
    }

    public int getProbability() {
        return probability;
    }

    public int getNumber() {
        return number;
    }
    public Constructor<? extends NPC> getConstructor() {
        return constructor;
    }

}
