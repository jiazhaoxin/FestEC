package com.diabin.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by admin on 2019/3/13.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue65c'),//直接输入&#xe65c;不通过需改成\ue65c
    icon_ali_pay('\ue717');

    private char character;

    EcIcons(char character){
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
