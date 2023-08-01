package auxiliary;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

public enum Keys {
    FLY(KeyEvent.VK_SPACE),
    Q_SKILL(KeyEvent.VK_Q),
    E_SKILL(KeyEvent.VK_E);
    private static final Set<Integer> keySet = new HashSet<>();

    Keys(int keyValue){
        this.keyValue = keyValue;
    }
    private int keyValue;

    public boolean use(){
        if(keyValue != Keys.FLY.keyValue && keySet.contains(keyValue)){
            CommonUtils.task(1500, 0, 1, () -> {
                keySet.remove(keyValue);
            });
            return true;
        }
        return keySet.contains(keyValue);
    }

    /**
     * 记录按下按键
     * @param keyCode
     */
    public static void add(int keyCode){
        keySet.add(keyCode);
    }

    /**
     * 记录移除按键
     * @param keyCode
     */
    public static void remove(int keyCode){
        keySet.remove(keyCode);
    }

    /**
     * 使两个hashset值相等而引用保持不变
     * @param sourceSet
     * @param targetSet
     */
    public static void copyHashSet(HashSet<Integer> sourceSet, HashSet<Integer> targetSet) {
        targetSet.clear();
        targetSet.addAll(sourceSet);
    }
}
