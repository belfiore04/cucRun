package base;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 提供对List的添加、删除等功能
 * @param <T>
 */
public interface IBaseService<T> extends Drawable {
    /**
     * 初始化清除列表元素
     */
    void init();
    void add(T element);
    void remove(T element);

    /**
     * 获取元素列表
     * @return
     */
    CopyOnWriteArrayList<T> getElementList();

    /**
     * 为了实现人物向右移动，让背景和障碍物一起向左运动
     */
    void RelativelyMoveToLeft();
}
