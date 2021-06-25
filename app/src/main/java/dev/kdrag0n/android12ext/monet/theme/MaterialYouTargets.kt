package dev.kdrag0n.android12ext.monet.theme

import dev.kdrag0n.android12ext.monet.colors.Color
import dev.kdrag0n.android12ext.monet.colors.Oklch

/*
 * Default target colors, conforming to Material You standards.
 *
 * Derived from AOSP and Pixel defaults.
 */
class MaterialYouTargets(
    private val chromaFactor: Float = 1.0f,
) : ColorScheme() {
    companion object {
        // Lightness from AOSP defaults
        private val LIGHTNESS_MAP = mapOf(
            0    to 1.0f,
            10   to 0.9880873963836093f,
            50   to 0.9551400440214246f,
            100  to 0.9127904082618294f,
            200  to 0.8265622041716898f,
            300  to 0.7412252673769428f,
            400  to 0.653350946076347f,
            500  to 0.5624050605208273f,
            600  to 0.48193149058901036f,
            700  to 0.39417829080418526f,
            800  to 0.3091856317280812f,
            900  to 0.22212874192541768f,
            1000 to 0.0f,
        )

        // Accent chroma from Pixel defaults
        // We use the most chromatic color as the reference
        // A-1 chroma = avg(default Pixel Blue shades 100-900)
        // Excluding very bright variants (10, 50) to avoid light bias
        // A-1 > A-3 > A-2
        private const val ACCENT1_CHROMA = 0.1328123146401862f
        private const val ACCENT2_CHROMA = ACCENT1_CHROMA / 3
        private const val ACCENT3_CHROMA = ACCENT2_CHROMA * 2

        // Neutral chroma derived from Google's CAM16 implementation
        // N-2 > N-1
        private const val NEUTRAL1_CHROMA = ACCENT1_CHROMA / 12
        private const val NEUTRAL2_CHROMA = NEUTRAL1_CHROMA * 2
    }

    override val neutral1 = shadesWithChroma(NEUTRAL1_CHROMA)
    override val neutral2 = shadesWithChroma(NEUTRAL2_CHROMA)

    override val accent1 = shadesWithChroma(ACCENT1_CHROMA)
    override val accent2 = shadesWithChroma(ACCENT2_CHROMA)
    override val accent3 = shadesWithChroma(ACCENT3_CHROMA)

    private fun shadesWithChroma(chroma: Float): Map<Int, Color> {
        // Adjusted chroma
        val chromaAdj = chroma * chromaFactor

        return LIGHTNESS_MAP.map {
            it.key to Oklch(it.value, chromaAdj, 0.0f)
        }.toMap()
    }
}
