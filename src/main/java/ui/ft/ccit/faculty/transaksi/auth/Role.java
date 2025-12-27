package ui.ft.ccit.faculty.transaksi.auth;

/**
 * User roles for role-based access control.
 * 
 * <p>Defines the different permission levels in the system:</p>
 * <ul>
 *   <li><strong>ADMIN</strong>: Full access to all operations including user management</li>
 *   <li><strong>MANAGER</strong>: Can manage transactions, inventory, and view reports</li>
 *   <li><strong>CASHIER</strong>: Can create and view transactions, limited access</li>
 * </ul>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public enum Role {
	/**
	 * Administrator role with full system access
	 */
	ADMIN,
	
	/**
	 * Manager role with elevated permissions
	 */
	MANAGER,
	
	/**
	 * Cashier role with basic transaction permissions
	 */
	CASHIER
}
