package co.uzzu.structurizr.ktx.dsl.view

import co.uzzu.structurizr.ktx.dsl.ApplyBlock
import com.structurizr.model.Element
import com.structurizr.model.Relationship
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.LayoutMergeStrategy
import com.structurizr.view.PaperSize
import com.structurizr.view.View
import java.lang.UnsupportedOperationException

class ViewScope<TView : View>(
    internal val view: TView,
    internal val inclusionScope: InclusionScope<TView> = InclusionScope(view),
    internal val exclusionScope: ExclusionScope<TView> = ExclusionScope(view)
)

class InclusionScope<TView : View>(internal val view: TView)
class ExclusionScope<TView : View>(internal val view: TView)

// region ViewScope

/**
 * @see [View.title]
 */
var <TView : View> ViewScope<TView>.title: String?
    get() = view.title
    set(value) {
        view.title = value
    }

/**
 * @see [View.description]
 */
var <TView : View> ViewScope<TView>.description: String?
    get() = view.description
    set(value) {
        view.description = value
    }

/**
 * @see [View.paperSize]
 */
var <TView : View> ViewScope<TView>.paperSize: PaperSize
    get() = view.paperSize
    set(value) {
        view.paperSize = value
    }

/**
 * @see [View.layoutMergeStrategy]
 */
var <TView : View> ViewScope<TView>.layoutMergeStrategy: LayoutMergeStrategy
    get() = throw UnsupportedOperationException()
    set(value) {
        view.setLayoutMergeStrategy(value)
    }

/**
 * @see [InclusionScope]
 */
fun <TView : View> ViewScope<TView>.includes(block: ApplyBlock<InclusionScope<TView>>) {
    inclusionScope.apply(block)
}

/**
 * @see [ExclusionScope]
 */
fun <TView : View> ViewScope<TView>.excludes(block: ApplyBlock<ExclusionScope<TView>>) {
    exclusionScope.apply(block)
}

// region AutomaticLayout

/**
 * @see [AutomaticLayoutConfig]
 */
val <TView : View> ViewScope<TView>.automaticLayout: AutomaticLayoutConfig
    get() = AutomaticLayoutConfig(view)

/**
 * @see [View.enableAutomaticLayout]
 */
fun <TView : View> ViewScope<TView>.Dagre(
    rankDirection: AutomaticLayout.RankDirection,
    rankSeparation: Int,
    nodeSeparation: Int,
    edgeSeparation: Int,
    vertices: Boolean
): AutomaticLayoutParam.Dagre =
    AutomaticLayoutParam.Dagre(
        rankDirection,
        rankSeparation,
        nodeSeparation,
        edgeSeparation,
        vertices
    )

/**
 * @see [View.enableAutomaticLayout]
 */
fun <TView : View> ViewScope<TView>.Graphviz(
    rankDirection: AutomaticLayout.RankDirection,
    rankSeparation: Int = 300,
    nodeSeparation: Int = 300
): AutomaticLayoutParam.Graphviz =
    AutomaticLayoutParam.Graphviz(
        rankDirection,
        rankSeparation,
        nodeSeparation
    )

class AutomaticLayoutConfig(
    private val view: View
) {

    /**
     * @see [View.disableAutomaticLayout]
     */
    fun disable() {
        view.disableAutomaticLayout()
    }

    /**
     * @see [View.enableAutomaticLayout]
     */
    fun enable(param: AutomaticLayoutParam? = null) {
        when (param) {
            is AutomaticLayoutParam.Dagre -> {
                view.enableAutomaticLayout(
                    param.rankDirection,
                    param.rankSeparation,
                    param.nodeSeparation,
                    param.edgeSeparation,
                    param.vertices
                )
            }
            is AutomaticLayoutParam.Graphviz -> {
                view.enableAutomaticLayout(
                    param.rankDirection,
                    param.rankSeparation,
                    param.nodeSeparation
                )
            }
            null -> {
                view.enableAutomaticLayout()
            }
        }
    }
}

/**
 * @see [View.enableAutomaticLayout]
 */
sealed class AutomaticLayoutParam {
    /**
     * Parameter for Dagre implementation
     */
    data class Dagre
    internal constructor(
        val rankDirection: AutomaticLayout.RankDirection,
        val rankSeparation: Int,
        val nodeSeparation: Int,
        val edgeSeparation: Int,
        val vertices: Boolean
    ) : AutomaticLayoutParam()

    /**
     * Parameter for Graphviz implementation
     */
    data class Graphviz
    internal constructor(
        val rankDirection: AutomaticLayout.RankDirection,
        val rankSeparation: Int,
        val nodeSeparation: Int
    ) : AutomaticLayoutParam()
}

// endregion

// endregion

// region InclusionScope

// endregion

// region ExclusionScope

/**
 * @see [View.remove]
 */
fun <TView : View> ExclusionScope<TView>.relationship(relationship: Relationship) {
    view.remove(relationship)
}

/**
 * @see [View.removeRelationshipsNotConnectedToElement]
 */
fun <TView : View> ExclusionScope<TView>.relationshipsNotConnectedToElement(element: Element) {
    view.removeRelationshipsNotConnectedToElement(element)
}

/**
 * @see [View.removeElementsWithNoRelationships]
 */
fun <TView : View> ExclusionScope<TView>.elementsWithNoRelationships() {
    view.removeElementsWithNoRelationships()
}

// endregion
