@Transactional
    public void pushToGithub(String email, GithubCodePushRequestDto requestDto) throws IOException {
        String codePath = requestDto.title() + "/code." + requestDto.language();
        String readmePath = requestDto.title() + "/description.md";
        
        String githubToken = githubTokenRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_GITHUB_TOKEN)
        ).getGithubToken();

        GitHub github = new GitHubBuilder().withOAuthToken(githubToken).build();
        GHRepository repo = getOrCreateRepository(github, getGithubName(github));

        pushFile(repo, codePath, requestDto.code(), "Initial commit: code", "Updated code");
        pushFile(repo, readmePath, requestDto.description(), "Initial commit: description", "Updated README");

    }