package tobystudyproject.tobystudyproject.service;

import tobystudyproject.tobystudyproject.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
    void upgradeLevel(User user);
}
