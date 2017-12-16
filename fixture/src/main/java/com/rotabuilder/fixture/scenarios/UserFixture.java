package com.rotabuilder.fixture.scenarios;

import com.google.common.collect.Lists;
import com.rotabuilder.fixture.dom.SkillCreate;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.dom.user.AccountType;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;
import org.isisaddons.module.security.seed.scripts.AbstractTenancyFixtureScript;
import org.isisaddons.module.security.seed.scripts.AbstractUserAndRolesFixtureScript;

public class UserFixture extends FixtureScript {

    public UserFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(final ExecutionContext ec) {
        final SkillCreate fs = new SkillCreate().setName("test");

        ec.executeChild(this, fs);
        ec.executeChild(this, new RoleAndPermissionsFixtureScript("easter2017", "Easter 2017 Concert"));
        ec.executeChild(this, new RoleAndPermissionsFixtureScript("summer2017", "Summer 2017 Concert"));
        ec.executeChild(this, new RoleAndPermissionsFixtureScript("christmas2017", "Christmas 2017 Concert"));

        ec.executeChild(this, new AbstractTenancyFixtureScript(){
            @Override
            protected void execute(ExecutionContext executionContext) {
                create("bulgaria", "/bg", null, executionContext);

            }
        });

        ec.executeChild(this, new AbstractRoleAndPermissionsFixtureScript("todoapp-sessionlogger-admin", "Admin access to session logger module"){
            @Override
            protected void execute(ExecutionContext executionContext) {
                newPackagePermissions(ApplicationPermissionRule.ALLOW, ApplicationPermissionMode.CHANGING, "org.isisaddons.module.sessionlogger");
            }
        });

        ec.executeChild(this, new AbstractRoleAndPermissionsFixtureScript("todoapp-auditing-admin", "Admin access to audit module"){
            @Override
            protected void execute(ExecutionContext executionContext) {
                newPackagePermissions(ApplicationPermissionRule.ALLOW, ApplicationPermissionMode.CHANGING, "org.isisaddons.module.audit");
            }
        });

        ec.executeChild(this, new AbstractRoleAndPermissionsFixtureScript("todoapp-settings-admin", "Admin access to settings module"){
            @Override
            protected void execute(ExecutionContext executionContext) {
                newPackagePermissions(ApplicationPermissionRule.ALLOW, ApplicationPermissionMode.CHANGING, "org.isisaddons.module.settings");
            }
        });

        ec.executeChild(this, new AbstractRoleAndPermissionsFixtureScript("todoapp-command-admin", "Admin access to command module"){
            @Override
            protected void execute(ExecutionContext executionContext) {
                newPackagePermissions(ApplicationPermissionRule.ALLOW, ApplicationPermissionMode.CHANGING, "org.isisaddons.module.command");
            }
        });



        ec.executeChild(this, new AbstractUserAndRolesFixtureScript("test", "test", AccountType.LOCAL, Lists.newArrayList("easter2017","christmas2017","isis-module-security-regular-user")){});
        ec.executeChild(this, new AbstractUserAndRolesFixtureScript("user", "user", null, "/", AccountType.LOCAL, Lists.newArrayList("easter2017","christmas2017","isis-module-security-regular-user")){});
        ec.executeChild(this, new AbstractUserAndRolesFixtureScript("admin", "admin", null, "/bg", AccountType.LOCAL, Lists.newArrayList("easter2017", "summer2017","isis-module-security-admin", "todoapp-auditing-admin", "todoapp-sessionlogger-admin", "todoapp-settings-admin", "todoapp-command-admin")){});
    }

    private static class RoleAndPermissionsFixtureScript extends AbstractRoleAndPermissionsFixtureScript {
        public RoleAndPermissionsFixtureScript(String roleName, String roleDescriptionIfAny) {
            super(roleName, roleDescriptionIfAny);
        }

        @Override
        protected void execute(ExecutionContext ec) {
            newPackagePermissions(ApplicationPermissionRule.ALLOW, ApplicationPermissionMode.CHANGING, "com.rotabuilder");
        }
    }
}
