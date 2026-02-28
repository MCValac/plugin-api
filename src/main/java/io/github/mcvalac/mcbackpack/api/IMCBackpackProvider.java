package io.github.mcvalac.mcbackpack.api;

import io.github.mcvalac.mcbackpack.api.model.BackpackData;
import java.util.concurrent.CompletableFuture;

/**
 * The primary service provider interface for the MCBackpack system.
 * <p>
 * This interface acts as a high-level abstraction layer over the database, allowing
 * plugins to interact with backpack data without dealing directly with the underlying
 * storage implementation.
 * </p>
 */
public interface IMCBackpackProvider {

    /**
     * Initiates the creation of a new backpack.
     *
     * @param uuid    The unique identifier for the new backpack.
     * @param texture The texture string for the backpack item.
     * @param size    The storage capacity of the backpack.
     * @return A {@link CompletableFuture} that completes when the creation is finished.
     */
    CompletableFuture<Void> create(String uuid, String texture, int size);

    /**
     * Retrieves the data for an existing backpack.
     *
     * @param uuid The unique identifier of the backpack.
     * @return A {@link CompletableFuture} containing the {@link BackpackData} if found.
     */
    CompletableFuture<BackpackData> open(String uuid);

    /**
     * Sets a password for a backpack.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param pwdHash The hashed password to set.
     * @return A {@link CompletableFuture} that completes when the password is set.
     */
    CompletableFuture<Void> setPwd(String uuid, String pwdHash);

    /**
     * Validates an input credential against the stored password.
     *
     * @param uuid      The unique identifier of the backpack.
     * @param inputHash The credential to check.
     * @return A {@link CompletableFuture} containing {@code true} if valid, {@code false} otherwise.
     */
    CompletableFuture<Boolean> checkPwd(String uuid, String inputHash);

    /**
     * Changes an existing password to a new one.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param newHash The new hashed password.
     * @return A {@link CompletableFuture} that completes when the password is updated.
     */
    CompletableFuture<Void> changePwd(String uuid, String newHash);

    /**
     * Deletes the password protection from a backpack.
     *
     * @param uuid      The unique identifier of the backpack.
     * @param inputHash The credential required to verify ownership before deletion.
     * @return A {@link CompletableFuture} containing {@code true} if the operation was successful.
     */
    CompletableFuture<Boolean> deletePwd(String uuid, String inputHash);

    /**
     * Persists the current inventory state of a backpack.
     *
     * @param uuid          The unique identifier of the backpack.
     * @param contentBase64 The serialized inventory data.
     * @return A {@link CompletableFuture} that completes when the data is saved.
     */
    CompletableFuture<Void> save(String uuid, String contentBase64);

    /**
     * Updates the texture of a backpack.
     *
     * @param uuid    The unique identifier of the backpack.
     * @param texture The new texture string.
     * @return A {@link CompletableFuture} that completes when the texture is updated.
     */
    CompletableFuture<Void> setTexture(String uuid, String texture);

    /**
     * Shuts down the provider and closes any underlying connections.
     */
    void close();
}
