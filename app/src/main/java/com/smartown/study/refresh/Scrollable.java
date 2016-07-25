package com.smartown.study.refresh;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-25 9:31
 * <p>
 * 描述：判断滚动控件的状态
 */
public interface Scrollable {


    /**
     * @return 不能继续向上滚动，即滚动到顶部
     */
    boolean cannotScrollUp();


    /**
     * @return 不能继续向下滚动，即滚动到底部
     */
    boolean cannotScrollDown();

}
