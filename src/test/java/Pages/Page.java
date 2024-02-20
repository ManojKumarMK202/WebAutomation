package Pages;

import base.BaseUtils;

public abstract class Page extends BaseUtils {

    public Page() {
        this.waitForPageToLoad();
    }

    public abstract <T extends Page> T waitForPageToLoad();
}
