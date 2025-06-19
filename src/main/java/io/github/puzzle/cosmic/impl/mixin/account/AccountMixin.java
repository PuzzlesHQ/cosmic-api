package io.github.puzzle.cosmic.impl.mixin.account;

import finalforeach.cosmicreach.accounts.Account;
import io.github.puzzle.cosmic.api.account.IAccount;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;

@Internal
@Mixin(Account.class)
public abstract class AccountMixin implements IAccount {

}
