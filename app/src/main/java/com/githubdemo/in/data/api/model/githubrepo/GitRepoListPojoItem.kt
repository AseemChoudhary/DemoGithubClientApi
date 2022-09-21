package com.githubdemo.`in`.data.api.model.githubrepo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "github_item")
data class GitRepoListPojoItem(
    @ColumnInfo
            @SerializedName("allow_forking")
    var allowForking: Boolean,
    @ColumnInfo        @SerializedName("archive_url")
    var archiveUrl: String?,
    @ColumnInfo        @SerializedName("archived")
    var archived: Boolean,
    @ColumnInfo        @SerializedName("assignees_url")
    var assigneesUrl: String?,
    @ColumnInfo        @SerializedName("blobs_url")
    var blobsUrl: String?,
    @ColumnInfo        @SerializedName("branches_url")
    var branchesUrl: String?,
    @ColumnInfo        @SerializedName("clone_url")
    var cloneUrl: String?,
    @ColumnInfo        @SerializedName("collaborators_url")
    var collaboratorsUrl: String?,
    @ColumnInfo        @SerializedName("comments_url")
    var commentsUrl: String?,
    @ColumnInfo        @SerializedName("commits_url")
    var commitsUrl: String?,
    @ColumnInfo        @SerializedName("compare_url")
    var compareUrl: String?,
    @ColumnInfo        @SerializedName("contents_url")
    var contentsUrl: String?,
    @ColumnInfo        @SerializedName("contributors_url")
    var contributorsUrl: String?,
    @ColumnInfo        @SerializedName("created_at")
    var createdAt: String?,
    @ColumnInfo        @SerializedName("default_branch")
    var defaultBranch: String?,
    @ColumnInfo        @SerializedName("deployments_url")
    var deploymentsUrl: String?,
    @ColumnInfo        @SerializedName("description")
    var description: String?,
    @ColumnInfo        @SerializedName("disabled")
    var disabled: Boolean,
    @ColumnInfo        @SerializedName("downloads_url")
    var downloadsUrl: String?,
    @ColumnInfo        @SerializedName("events_url")
    var eventsUrl: String?,
    @ColumnInfo        @SerializedName("fork")
    var fork: Boolean,
    @ColumnInfo        @SerializedName("forks")
    var forks: Int,
    @ColumnInfo        @SerializedName("forks_count")
    var forksCount: Int,
    @ColumnInfo        @SerializedName("forks_url")
    var forksUrl: String?,
    @ColumnInfo        @SerializedName("full_name")
    var fullName: String?,
    @ColumnInfo        @SerializedName("git_commits_url")
    var gitCommitsUrl: String?,
    @ColumnInfo        @SerializedName("git_refs_url")
    var gitRefsUrl: String?,
    @ColumnInfo        @SerializedName("git_tags_url")
    var gitTagsUrl: String?,
    @ColumnInfo        @SerializedName("git_url")
    var gitUrl: String?,
    @ColumnInfo        @SerializedName("has_downloads")
    var hasDownloads: Boolean,
    @ColumnInfo        @SerializedName("has_issues")
    var hasIssues: Boolean,
    @ColumnInfo        @SerializedName("has_pages")
    var hasPages: Boolean,
    @ColumnInfo        @SerializedName("has_projects")
    var hasProjects: Boolean,
    @ColumnInfo        @SerializedName("has_wiki")
    var hasWiki: Boolean,
    @ColumnInfo        @SerializedName("homepage")
    var homepage: String?,
    @ColumnInfo        @SerializedName("hooks_url")
    var hooksUrl: String?,
    @ColumnInfo        @SerializedName("html_url")
    var htmlUrl: String?,
    @PrimaryKey       @SerializedName("id")
    var id: Int,
    @ColumnInfo        @SerializedName("is_template")
    var isTemplate: Boolean,
    @ColumnInfo        @SerializedName("issue_comment_url")
    var issueCommentUrl: String?,
    @ColumnInfo        @SerializedName("issue_events_url")
    var issueEventsUrl: String?,
    @ColumnInfo        @SerializedName("issues_url")
    var issuesUrl: String?,
    @ColumnInfo        @SerializedName("keys_url")
    var keysUrl: String?,
    @ColumnInfo        @SerializedName("labels_url")
    var labelsUrl: String?,
    @ColumnInfo        @SerializedName("language")
    var language: String?,
    @ColumnInfo        @SerializedName("languages_url")
    var languagesUrl: String?,
    @ColumnInfo        @SerializedName("merges_url")
    var mergesUrl: String?,
    @ColumnInfo        @SerializedName("milestones_url")
    var milestonesUrl: String?,
    @Ignore        @SerializedName("mirror_url")
    var mirrorUrl: String,
    @ColumnInfo        @SerializedName("name")
    var name: String?,
    @ColumnInfo        @SerializedName("node_id")
    var nodeId: String?,
    @ColumnInfo        @SerializedName("notifications_url")
    var notificationsUrl: String?,
    @ColumnInfo        @SerializedName("open_issues")
    var openIssues: Int,
    @ColumnInfo        @SerializedName("open_issues_count")
    var openIssuesCount: Int,
//    @ColumnInfo        @SerializedName("owner")
//    var owner: Owner,
    @ColumnInfo        @SerializedName("private")
    var `private`: Boolean,
    @ColumnInfo        @SerializedName("pulls_url")
    var pullsUrl: String?,
    @ColumnInfo        @SerializedName("pushed_at")
    var pushedAt: String?,
    @ColumnInfo        @SerializedName("releases_url")
    var releasesUrl: String?,
    @ColumnInfo        @SerializedName("size")
    var size: Int,
    @ColumnInfo        @SerializedName("ssh_url")
    var sshUrl: String?,
    @ColumnInfo        @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @ColumnInfo        @SerializedName("stargazers_url")
    var stargazersUrl: String?,
    @ColumnInfo        @SerializedName("statuses_url")
    var statusesUrl: String?,
    @ColumnInfo        @SerializedName("subscribers_url")
    var subscribersUrl: String?,
    @ColumnInfo        @SerializedName("subscription_url")
    var subscriptionUrl: String?,
    @ColumnInfo        @SerializedName("svn_url")
    var svnUrl: String?,
    @ColumnInfo        @SerializedName("tags_url")
    var tagsUrl: String?,
    @ColumnInfo        @SerializedName("teams_url")
    var teamsUrl: String?,
//    @ColumnInfo        @SerializedName("topics")
//    var topics: List<String?>,
    @ColumnInfo        @SerializedName("trees_url")
    var treesUrl: String?,
    @ColumnInfo        @SerializedName("updated_at")
    var updatedAt: String?,
    @ColumnInfo        @SerializedName("url")
    var url: String?,
    @ColumnInfo        @SerializedName("visibility")
    var visibility: String?,
    @ColumnInfo        @SerializedName("watchers")
    var watchers: Int,
    @ColumnInfo        @SerializedName("watchers_count")
    var watchersCount: Int,
    @ColumnInfo        @SerializedName("web_commit_signoff_required")
    var webCommitSignoffRequired: Boolean
): Serializable {

    constructor():this(false, "", false, "",
        "", "", "", "",
        "", "", "", "",
        "", "", "", "",
        "", false, "", "" +
                "", false, 0, 0, "", "",
        "", "", "", "", false,
        false, false, false, false, "", "", "",
        0, false, "", "", "", "", "",
        "", "", "", "", "", "", "", "",
        0, 0, false, "", "", "", 0, "",0,
         "", "", "", "", "", "", "",
        "", "", "", "", 0, 0, false)


}