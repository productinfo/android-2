package org.cryptomator.data.cloud.crypto

import org.cryptomator.domain.Cloud
import org.cryptomator.domain.CloudFile
import java.util.Date

class CryptoSymlink(
	override val parent: CryptoFolder, override val name: String, override val path: String, private val target: String,
	/**
	 * @return The actual file in the underlying, i.e. decorated, CloudContentRepository
	 */
	val cloudFile: CloudFile
) : CloudFile, CryptoNode {

	override val cloud: Cloud?
		get() = parent.cloud

	override val size: Long
		get() = target.length.toLong()

	override val modified: Date?
		get() = cloudFile.modified

	override fun equals(other: Any?): Boolean {
		if (other == null || javaClass != other.javaClass) {
			return false
		}
		return if (other === this) {
			true
		} else internalEquals(other as CryptoSymlink)
	}

	private fun internalEquals(obj: CryptoSymlink): Boolean {
		return path == obj.path
	}

	override fun hashCode(): Int {
		return path.hashCode()
	}
}
