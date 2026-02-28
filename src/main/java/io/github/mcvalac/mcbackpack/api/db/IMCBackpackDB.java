package io.github.mcvalac.mcbackpack.api.db;

import io.github.mcvalac.mcbackpack.api.model.BackpackData;
import java.util.concurrent.CompletableFuture;

/**
 * Interface defining the contract for database operations related to backpack storage.
 * <p>
 * Implementations should handle the low-level data persistence (SQL, Flatfile, etc.)
 * efficiently, utilizing asynchronous patterns where appropriate.
 * </p>
 */
public interface IMCBackpackDB {

    /**
     * Creates a new backpack record in the database.
     *
     * @param uuid    The unique identifier for the backpack.
     * @param texture The Base64 encoded texture string for the backpack's item icon.
     * @param size    The size of the backpack (number of slots).
     * @return A {@link CompletableFuture} representing the completion of the creation operation.
     */
    CompletableFuture<Void> create(String uuid, String texture, int size);

    /**
     * Loads backpack data associated with the given UUID.
     *
     * @param uuid The unique identifier of the backpack to load.
     * @return A {@link CompletableFuture} containing the {@link BackpackData} if found, or null if not present.
     */
    CompletableFuture<BackpackData> open(String uuid);
    
    /**
     * Updates the password hash for a specific backpack.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param pwdHash The new hashed password string to store.
     * @return A {@link CompletableFuture} representing the completion of the update operation.
     */
    CompletableFuture<Void> setPwd(String uuid, String pwdHash);
    
    /**
     * Verifies if the provided input matches the stored password hash.
     *
     * @param uuid      The unique identifier of the backpack.
     * @param inputHash The raw input or hash to compare against the stored credentials.
     * @return A {@link CompletableFuture} containing {@code true} if the password is correct, {@code false} otherwise.
     */
    CompletableFuture<Boolean> checkPwd(String uuid, String inputHash);
    
    /**
     * Changes the password for a specific backpack to a new hash.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param newHash The new hashed password string.
     * @return A {@link CompletableFuture} representing the completion of the update operation.
     */
    CompletableFuture<Void> changePwd(String uuid, String newHash);
    
    /**
     * Removes the password protection from a backpack if the input credential matches.
     *
     * @param uuid      The unique identifier of the backpack.
     * @param inputHash The credential required to authorize the removal.
     * @return A {@link CompletableFuture} containing {@code true} if the password was successfully removed, {@code false} otherwise.
     */
    CompletableFuture<Boolean> deletePwd(String uuid, String inputHash);
    
    /**
     * Saves the serialized inventory content to the database.
     *
     * @param uuid          The unique identifier of the backpack.
     * @param contentBase64 The Base64 encoded string representing the inventory's contents.
     * @return A {@link CompletableFuture} representing the completion of the save operation.
     */
    CompletableFuture<Void> save(String uuid, String contentBase64);

    /**
     * Updates the texture string for a specific backpack.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param texture The new Base64 texture string.
     * @return A {@link CompletableFuture} representing the completion of the update operation.
     */
    CompletableFuture<Void> setTexture(String uuid, String texture);
    
    /**
     * Closes the database connection and releases associated resources.
     */
    void close();
}
