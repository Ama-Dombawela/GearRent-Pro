package com.ijse.GearRentPro.util;

import com.ijse.GearRentPro.dto.UserDto;

public final class AuthUtil {

    private AuthUtil() {
    }

    public static UserDto requireUser() throws Exception {
        UserDto user = Session.getLoggedInUser();
        if (user == null) {
            throw new Exception("Access denied: user is not logged in.");
        }
        return user;
    }

    public static void requireAdmin() throws Exception {
        // Role R001 is treated as the admin account.
        UserDto user = requireUser();
        if (!"R001".equals(user.getRoleId())) {
            throw new Exception("Access denied: admin privileges are required.");
        }
    }

    public static void requireBranchScopedAccess(String branchId) throws Exception {
        UserDto user = requireUser();
        String roleId = user.getRoleId();

        // Admin can access any branch.
        if ("R001".equals(roleId)) {
            return;
        }

        // Non-admin users must stay within their own branch.
        if (branchId == null || branchId.isBlank()) {
            throw new Exception("Access denied: branch is required for this operation.");
        }

        if (!branchId.equals(user.getBranchId())) {
            throw new Exception("Access denied: cross-branch operation is not allowed.");
        }
    }

    public static void requireAdminOrManager() throws Exception {
        // Admin and manager accounts are allowed here.
        UserDto user = requireUser();
        if (!"R001".equals(user.getRoleId()) && !"R002".equals(user.getRoleId())) {
            throw new Exception("Access denied: only admin or branch manager can perform this action.");
        }
    }
}
