package io.github.mcvalac.mcbackpack.api.model;

/**
 * Represents the immutable state of a backpack retrieved from storage.
 * <p>
 * This data object holds identification, visual properties, security credentials,
 * and the actual inventory content of a backpack.
 * </p>
 */
public class BackpackData {
    
    /**
     * The unique identifier associated with this backpack.
     */
    private final String uuid;

    /**
     * The Base64 string representing the texture of the backpack item (usually a player head).
     */
    private final String texture;

    /**
     * The stored password hash for the backpack. Null or empty implies no password protection.
     */
    private final String pwdHash;

    /**
     * The capacity of the backpack in slots (e.g., 9, 27, 54).
     */
    private final int size;

    /**
     * The serialized inventory content in Base64 format.
     */
    private final String content;

    /**
     * Constructs a new BackpackData instance.
     *
     * @param uuid    The unique identifier.
     * @param texture The visual texture string.
     * @param pwdHash The password hash (nullable).
     * @param size    The size of the inventory.
     * @param content The serialized content string (nullable).
     */
    public BackpackData(String uuid, String texture, String pwdHash, int size, String content) {
        this.uuid = uuid;
        this.texture = texture;
        this.pwdHash = pwdHash;
        this.size = size;
        this.content = content;
    }

    /**
     * Gets the unique identifier of the backpack.
     *
     * @return The UUID string.
     */
    public String getUuid() { return uuid; }

    /**
     * Gets the texture string for the backpack item.
     *
     * @return The Base64 texture string.
     */
    public String getTexture() { return texture; }

    /**
     * Gets the stored password hash.
     *
     * @return The password hash string, or null if not set.
     */
    public String getPwdHash() { return pwdHash; }

    /**
     * Gets the size of the backpack inventory.
     *
     * @return The number of slots.
     */
    public int getSize() { return size; }

    /**
     * Gets the serialized inventory content.
     *
     * @return The Base64 content string, or null if the inventory is empty.
     */
    public String getContent() { return content; }

    /**
     * Checks if the backpack is protected by a password.
     *
     * @return {@code true} if a password hash exists and is not empty; {@code false} otherwise.
     */
    public boolean isLocked() { return pwdHash != null && !pwdHash.isEmpty(); }
}
