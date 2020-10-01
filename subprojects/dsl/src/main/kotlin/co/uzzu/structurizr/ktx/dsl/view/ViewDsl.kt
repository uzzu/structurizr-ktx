package co.uzzu.structurizr.ktx.dsl.view

import com.structurizr.model.Element
import com.structurizr.model.Relationship
import com.structurizr.view.AutomaticLayout
import com.structurizr.view.LayoutMergeStrategy
import com.structurizr.view.PaperSize
import com.structurizr.view.View

abstract class ViewScope
<TView : View, TInclusionScope : InclusionScope<TView>, TExclusionScope : ExclusionScope<TView>>
internal constructor(
    protected val view: TView,
    private val inclusionScope: TInclusionScope,
    private val exclusionScope: TExclusionScope
) {
    /**
     * @see [View.title]
     */
    var title: String?
        get() = view.title
        set(value) {
            view.title = value
        }

    /**
     * @see [View.description]
     */
    var description: String?
        get() = view.description
        set(value) {
            view.description = value
        }

    /**
     * @see [View.paperSize]
     */
    var paperSize: PaperSize
        get() = view.paperSize
        set(value) {
            view.paperSize = value
        }

    /**
     * @see [View.layoutMergeStrategy]
     */
    var layoutMergeStrategy: LayoutMergeStrategy
        get() = throw UnsupportedOperationException()
        set(value) {
            view.setLayoutMergeStrategy(value)
        }

    /**
     * @see [InclusionScope]
     */
    fun includes(block: TInclusionScope.() -> Unit) {
        inclusionScope.apply(block)
    }

    /**
     * @see [ExclusionScope]
     */
    fun excludes(block: TExclusionScope.() -> Unit) {
        exclusionScope.apply(block)
    }

    /**
     * @see [AutomaticLayoutScope]
     */
    fun automaticLayout(block: AutomaticLayoutScope.() -> Unit) {
        block(AutomaticLayoutScope(view))
    }
}

abstract class InclusionScope<TView : View>
internal constructor(
    protected val view: TView
)

abstract class ExclusionScope<TView : View>
internal constructor(
    protected val view: TView
) {

    /**
     * @see [View.remove]
     */
    fun relationship(relationship: Relationship) {
        view.remove(relationship)
    }

    /**
     * @see [View.removeRelationshipsNotConnectedToElement]
     */
    fun relationshipsNotConnectedToElement(element: Element) {
        view.removeRelationshipsNotConnectedToElement(element)
    }

    /**
     * @see [View.removeElementsWithNoRelationships]
     */
    fun elementsWithNoRelationships() {
        view.removeElementsWithNoRelationships()
    }
}

class AutomaticLayoutScope
internal constructor(
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

    /**
     * @see [View.enableAutomaticLayout]
     */
    fun Dagre(
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
    fun Graphviz(
        rankDirection: AutomaticLayout.RankDirection,
        rankSeparation: Int = 300,
        nodeSeparation: Int = 300
    ): AutomaticLayoutParam.Graphviz =
        AutomaticLayoutParam.Graphviz(
            rankDirection,
            rankSeparation,
            nodeSeparation
        )
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
